/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.api.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mifos.api.GenericResponse;
import com.mifos.api.model.APIEndPoint;
import com.mifos.objects.accounts.ClientAccounts;
import com.mifos.objects.client.ActivatePayload;
import com.mifos.objects.client.Address;
import com.mifos.objects.client.Client;
import com.mifos.objects.client.ClientAddressRequest;
import com.mifos.objects.client.ClientAddressResponse;
import com.mifos.objects.client.ClientPayload;
import com.mifos.objects.client.Page;
import com.mifos.objects.noncore.Identifier;
import com.mifos.objects.noncore.IdentifierPayload;
import com.mifos.objects.noncore.IdentifierTemplate;
import com.mifos.objects.templates.clients.ClientsTemplate;

import java.util.List;

import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author fomenkoo
 */
public interface ClientService {

    /**
     * @param b      True Enabling the Pagination of the API
     * @param offset Value give from which position Fetch ClientList
     * @param limit  Maximum size of the Client
     * @return List of Clients
     */
    @GET(APIEndPoint.CLIENTS)
    Observable<Page<Client>> getAllClients(@Query("paged") boolean b,
                                           @Query("offset") int offset,
                                           @Query("limit") int limit);

    @GET(APIEndPoint.CLIENTS + "/{clientId}")
    Observable<Client> getClient(@Path("clientId") int clientId);

    @Multipart
    @POST(APIEndPoint.CLIENTS + "/{clientId}/images")
    Observable<GenericResponse> uploadClientImage(@Path("clientId") int clientId,
                                                  @Part MultipartBody.Part file);

    @DELETE(APIEndPoint.CLIENTS + "/{clientId}/images")
    Observable<ResponseBody> deleteClientImage(@Path("clientId") int clientId);

    //TODO: Implement when API Fixed
//    @GET("/clients/{clientId}/images")
//    Observable<TypedString> getClientImage(@Path("clientId") int clientId);

    @POST(APIEndPoint.CLIENTS)
    Observable<Client> createClient(@Body ClientPayload clientPayload);

    @PUT(APIEndPoint.CLIENTS + "/{clientId}")
    Observable<GenericResponse> updateClient(
      @Path("clientId") long clientId,
      @Body ClientPayload clientPayload
    );

    @POST(APIEndPoint.CLIENTS)
    Observable<GenericResponse> createMifosClient(@Body ClientPayload clientPayload);


    @GET(APIEndPoint.CLIENTS + "/template")
    Observable<ClientsTemplate> getClientTemplate();

    @GET(APIEndPoint.CLIENTS + "/{clientId}/accounts")
    Observable<ClientAccounts> getClientAccounts(@Path("clientId") long clientId);

    /**
     * This Service is for fetching the List of Identifiers.
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/clients/{clientId}/identifiers
     *
     * @param clientId Client Id
     * @return List<Identifier>
     */
    @GET(APIEndPoint.CLIENTS + "/{clientId}/" + APIEndPoint.IDENTIFIERS)
    Observable<List<Identifier>> getClientIdentifiers(@Path("clientId") long clientId);

    @GET(APIEndPoint.CLIENTS + "/{clientId}/" + APIEndPoint.IDENTIFIERS)
    Observable<JsonArray> getClientIdentifiersJsonArray(@Path("clientId") long clientId);

    /**
     * This Service is for Creating the Client Identifier.
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/clients/{clientId}/identifiers
     *
     * @param clientId          Client Id
     * @param identifierPayload IdentifierPayload
     * @return SaveResponse
     */
    @POST(APIEndPoint.CLIENTS + "/{clientId}/identifiers")
    Observable<GenericResponse> createClientIdentifier(@Path("clientId") int clientId,
                                                       @Body IdentifierPayload identifierPayload);

    /**
     * This Service is for Creating the Client Identifier.
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/clients/{clientId}/identifiers
     *
     * @param clientId          Client Id
     * @param identifierPayload IdentifierPayload
     * @return SaveResponse
     */
    @POST(APIEndPoint.CLIENTS + "/{clientId}/identifiers")
    Observable<GenericResponse> createClientIdentifier(@Path("clientId") long clientId,
                                                       @Body Map<String, Object> identifierPayload);


    /**
     * This Service is for the Fetching the Client Identifier Template.
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/clients/{clientId}/identifiers/template
     *
     * @param clientId Client Id
     * @return IdentifierTemplate
     */
    @GET(APIEndPoint.CLIENTS + "/{clientId}/identifiers/template")
    Observable<IdentifierTemplate> getClientIdentifierTemplate(@Path("clientId") int clientId);

    /**
     * This Service is for the Fetching the Client Address Template.
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/client/addresses/template
     *
     * @return Address
     */
    @GET(APIEndPoint.CLIENT + "/addresses/template")
    Observable<Address> getClientAddressTemplate();

    /**
     * This Service is for the Fetching the Client Address Template.
     * REST END POINT:
     https://demo.openmf.org/fineract-provider/api/v1/client/11/addresses     *
     * @return Address
     */
    @GET(APIEndPoint.CLIENT + "/{clientId}/" + APIEndPoint.ADDRESSES)
    Observable<List<Address>> getClientAddresses(@Path("clientId") long clientId);

    /**
     * This Service is for the Fetching the Client Address Template.
     * REST END POINT:
     *https://demo.openmf.org/fineract-provider/api/v1/client/11/addresses?status=true&type=40&tenantIdentifier=default&pretty=true
     * @return Address
     */
    @GET(APIEndPoint.CLIENT + "/{clientId}/" + APIEndPoint.ADDRESSES)
    Observable<List<Address>> getClientAddress(
      @Path("clientId") long clientId,
      @Query("status") List<Boolean> status,
      @Query("type") long addressType
    );


    /**
     * This Service is for the Saving the Client Address.
     */
    @POST(APIEndPoint.CLIENT + "/{clientId}/" + APIEndPoint.ADDRESSES)
    Observable<GenericResponse> saveClientAddress(
      @Path("clientId") long clientId,
      @Query("type") long addressTypeId,
      @Body Address address
    );

    @PUT(APIEndPoint.CLIENT + "/{clientId}/" + APIEndPoint.ADDRESSES)
    Observable<GenericResponse> updateClientAddress(
      @Path("clientId") long clientId,
      @Query("type") long addressTypeId,
      @Body Address address
    );





    /**
     * This Service for Deleting the Client Identifier.
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/clients/{clientId}/identifiers/
     * {identifierId}
     *
     * @param clientId     Client Id
     * @param identifierId Identifier Id
     * @return GenericResponse
     */
    @DELETE(APIEndPoint.CLIENTS + "/{clientId}/" + APIEndPoint.IDENTIFIERS + "/{identifierId}")
    Observable<GenericResponse> deleteClientIdentifier(@Path("clientId") int clientId,
                                                                     @Path("identifierId") int identifierId);


    /**
     * This Service for Updating the Client Identifier.
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/clients/{clientId}/identifiers/
     * {identifierId}
     *
     * @param clientId     Client Id
     * @param identifierId Identifier Id
     * @return GenericResponse
     */
    @PUT(APIEndPoint.CLIENTS + "/{clientId}/" + APIEndPoint.IDENTIFIERS + "/{identifierId}")
    Observable<GenericResponse> updateClientIdentifier(
        @Path("clientId") int clientId,
        @Path("identifierId") int identifierId,
        @Body IdentifierPayload identifierPayload
    );

    /**
     * This Service for Updating the Client Identifier.
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/clients/{clientId}/identifiers/
     * {identifierId}
     *
     * @param clientId     Client Id
     * @param identifierId Identifier Id
     * @return GenericResponse
     */
    @PUT(APIEndPoint.CLIENTS + "/{clientId}/" + APIEndPoint.IDENTIFIERS + "/{identifierId}")
    Observable<GenericResponse> updateClientIdentifier(
        @Path("clientId") long clientId,
        @Path("identifierId") long identifierId,
        @Body Map<String, Object> identifierPayload
    );


    /**
     * This is the service for fetching the client pinpoint locations from the dataTable
     * "client_pinpoint_location". This DataTable entries are
     * 1. Place Id
     * 2. Place Address
     * 3. latitude
     * 4. longitude
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/datatables/client_pinpoint_location
     * /{appTableId}
     *
     * @param clientId Client Id
     * @return ClientAddressResponse
     */
    @GET(APIEndPoint.DATATABLES + "/client_pinpoint_location/{clientId}")
    Observable<List<ClientAddressResponse>> getClientPinpointLocations(
            @Path("clientId") int clientId);


    /**
     * This is the service for adding the new Client Pinpoint Location in dataTable
     * "client_pinpoint_location".
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/datatables/client_pinpoint_location
     * /{appTableId}
     *
     * @param clientId             Client Id
     * @param clientAddressRequest ClientAddress
     * @return GenericResponse
     */
    @POST(APIEndPoint.DATATABLES + "/client_pinpoint_location/{clientId}")
    Observable<GenericResponse> addClientPinpointLocation(
            @Path("clientId") int clientId,
            @Body ClientAddressRequest clientAddressRequest);


    /**
     * This is the service for deleting the pinpoint location from the DataTable
     * "client_pinpoint_location".
     * REST END POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/datatables/client_pinpoint_location
     * /{appTableId}/{datatableId}
     *
     * @param apptableId
     * @param datatableId
     * @return GenericResponse
     */
    @DELETE(APIEndPoint.DATATABLES + "/client_pinpoint_location/{apptableId}/{datatableId}")
    Observable<GenericResponse> deleteClientPinpointLocation(@Path("apptableId") int apptableId,
                                                                           @Path("datatableId") int datatableId);

    /**
     * This is the service for updating the pinpoint location from DataTable
     * "client_pinpoint_location"
     * REST ENT POINT:
     * https://demo.openmf.org/fineract-provider/api/v1/datatables/client_pinpoint_location
     * /{appTableId}/{datatableId}
     *
     * @param apptableId
     * @param datatableId
     * @param address     Client Address
     * @return GenericResponse
     */
    @PUT(APIEndPoint.DATATABLES + "/client_pinpoint_location/{apptableId}/{datatableId}")
    Observable<GenericResponse> updateClientPinpointLocation(@Path("apptableId") int apptableId,
                                                                           @Path("datatableId") int datatableId,
                                                                           @Body ClientAddressRequest address);

    /**
     * This is the service to activate the client
     * REST ENT POINT
     * https://demo.openmf.org/fineract-provider/api/v1/clients/{clientId}?command=activate
     *
     * @param clientId
     * @return GenericResponse
     */
    @POST(APIEndPoint.CLIENTS + "/{clientId}?command=activate")
    Observable<GenericResponse> activateClient(@Path("clientId") int clientId,
                                                             @Body ActivatePayload clientActivate);
}
