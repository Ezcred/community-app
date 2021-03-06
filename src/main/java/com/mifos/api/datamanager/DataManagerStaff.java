package com.mifos.api.datamanager;

import com.mifos.api.BaseApiManager;
import com.mifos.api.local.databasehelper.DatabaseHelperStaff;
import com.mifos.objects.organisation.Staff;
import com.mifos.utils.PrefManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Rajan Maurya on 7/7/16.
 */
@Singleton
public class DataManagerStaff {

    public final BaseApiManager mBaseApiManager;
    public final DatabaseHelperStaff mDatabaseHelperStaff;
    public final PrefManager prefManager;

    @Inject
    public DataManagerStaff(
      BaseApiManager baseApiManager,
      DatabaseHelperStaff databaseHelperStaff, PrefManager prefManager
    ) {
        mBaseApiManager = baseApiManager;
        mDatabaseHelperStaff = databaseHelperStaff;
        this.prefManager = prefManager;
    }


    /**
     * @param officeId
     * @return
     */
    public Observable<List<Staff>> getStaffInOffice(int officeId) {
        switch (prefManager.getUserStatus()) {
            case 0:
                return mBaseApiManager.getStaffApi().getStaffForOffice(officeId)
                        .concatMap(new Func1<List<Staff>, Observable<? extends List<Staff>>>() {
                            @Override
                            public Observable<? extends List<Staff>> call(List<Staff> staffs) {
                                mDatabaseHelperStaff.saveAllStaffOfOffices(staffs);
                                return Observable.just(staffs);
                            }
                        });
            case 1:
                /**
                 * return all List of Staffs of Office from DatabaseHelperOffices
                 */
                return mDatabaseHelperStaff.readAllStaffOffices(officeId);

            default:
                List<Staff> staffs = new ArrayList<>();
                return Observable.just(staffs);
        }

    }

  public Observable<Staff> getStaffDetails(long staffId) {
    return mBaseApiManager.getStaffApi().getStaffDetails(staffId);
  }
}
