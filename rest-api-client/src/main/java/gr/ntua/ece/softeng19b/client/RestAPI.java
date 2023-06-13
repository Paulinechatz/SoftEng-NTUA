package gr.ntua.ece.softeng19b.client;

import gr.ntua.ece.softeng19b.data.model.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.math.BigInteger;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class RestAPI {

    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static final String BASE_URL = "/energy/api";
    public static final String CUSTOM_HEADER = "X-OBSERVATORY-AUTH";

    static {
        System.setProperty("jdk.internal.httpclient.disableHostnameVerification", "true");
    }

    private final String urlPrefix;
    private final HttpClient client;

    private String token = null; //user is not logged in

    public RestAPI() throws RuntimeException {
        this("localhost", 8765);
    }

    public RestAPI(String host, int port) throws RuntimeException {
        try {
            this.client = newHttpClient();
        }
        catch(NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e.getMessage());
        }
        this.urlPrefix = "https://" + host + ":" + port + BASE_URL;
    }

    String urlForActualDataLoad(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualTotalLoad/" + encAreaName + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForActualDataLoadMonth(String areaName, String resolutionCode, String month, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualTotalLoad/" + encAreaName + "/" + encResCode + "/month/" + month +
                "?format=" + format.name().toLowerCase();
    }

    String urlForActualDataLoadYear(String areaName, String resolutionCode, String year, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualTotalLoad/" + encAreaName + "/" + encResCode + "/year/" + year +
                "?format=" + format.name().toLowerCase();
    }

    String urlForDayAheadDataLoad(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/DayAheadTotalLoadForecast/" + encAreaName + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForDayAheadDataLoadMonth(String areaName, String resolutionCode, String month, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/DayAheadTotalLoadForecast/" + encAreaName + "/" + encResCode + "/month/" + month +
                "?format=" + format.name().toLowerCase();
    }

    String urlForDayAheadDataLoadYear(String areaName, String resolutionCode, String year, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/DayAheadTotalLoadForecast/" + encAreaName + "/" + encResCode + "/year/" + year +
                "?format=" + format.name().toLowerCase();
    }

    String urlForAGPT(String areaName, String productionType, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        if (resolutionCode=="AllTypes"){
            String encProType  = resolutionCode;
            return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProType + "/" + encResCode + "/date/" + date.toString() +
                    "?format=" + format.name().toLowerCase();

        }
        else{
            String encProType  = URLEncoder.encode(productionType, StandardCharsets.UTF_8);
            return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProType + "/" + encResCode + "/date/" + date.toString() +
                    "?format=" + format.name().toLowerCase();

        }
    }

    String urlForAGPTMonth(String areaName, String productionType, String resolutionCode, String month, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        if (resolutionCode=="AllTypes"){
            String encProType  = resolutionCode;
            return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProType + "/" + encResCode + "/month/" + month +
                    "?format=" + format.name().toLowerCase();

        }
        else{
            String encProType  = URLEncoder.encode(productionType, StandardCharsets.UTF_8);
            return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProType + "/" + encResCode + "/month/" + month +
                    "?format=" + format.name().toLowerCase();

        }
    }

    String urlForAGPTYear(String areaName, String productionType, String resolutionCode, String year, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        if (resolutionCode=="AllTypes"){
            String encProType  = resolutionCode;
            return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProType + "/" + encResCode + "/year/" + year +
                    "?format=" + format.name().toLowerCase();

        }
        else{
            String encProType  = URLEncoder.encode(productionType, StandardCharsets.UTF_8);
            return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProType + "/" + encResCode + "/year/" + year +
                    "?format=" + format.name().toLowerCase();

        }
    }

    String urlForAVSF(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualvsForecast/" + encAreaName + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForAVSFMonth(String areaName, String resolutionCode, String month, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualvsForecast/" + encAreaName + "/" + encResCode + "/month/" + month +
                "?format=" + format.name().toLowerCase();
    }

    String urlForAVSFYear(String areaName, String resolutionCode, String year, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualvsForecast/" + encAreaName + "/" + encResCode + "/year/" + year +
                "?format=" + format.name().toLowerCase();
    }

    String urlForHealthCheck() {
        return urlPrefix + "/HealthCheck";
    }

    String urlForReset() { return urlPrefix + "/Reset"; }

    String urlForLogin() { return urlPrefix + "/Login"; }

    String urlForLogout() { return urlPrefix + "/Logout"; }

    String urlForAddUser() { return urlPrefix + "/Admin/users"; }

    String urlForUpdateUser(String username) {
        return urlPrefix + "/Admin/users/" + URLEncoder.encode(username, StandardCharsets.UTF_8);
    }

    String urlForGetUser(String username) {
        return urlForUpdateUser(username);
    }

    String urlForImport(String dataSet) {
        return urlPrefix + "/Admin/" + URLEncoder.encode(dataSet, StandardCharsets.UTF_8);
    }

    private HttpRequest newGetRequest(String url) {
        return newRequest("GET", url, URL_ENCODED, HttpRequest.BodyPublishers.noBody());
    }

    private HttpRequest newPostRequest(String url, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
        return newRequest("POST", url, contentType, bodyPublisher);
    }

    private HttpRequest newPutRequest(String url, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
        return newRequest("PUT", url, contentType, bodyPublisher);
    }

    private HttpRequest newRequest(String method,
                                   String url,
                                   String contentType,
                                   HttpRequest.BodyPublisher bodyPublisher) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
 //       System.out.println("before newrequest token is");
 //       System.out.println(token);
        if (token != null) {
            builder.header(CUSTOM_HEADER, token);
        }
        return builder.
                method(method, bodyPublisher).
                header(CONTENT_TYPE_HEADER, contentType).
                uri(URI.create(url)).
                build();
    }

    private <T> T sendRequestAndParseResponseBodyAsUTF8Text(Supplier<HttpRequest> requestSupplier,
                                                            Function<Reader, T> bodyProcessor) {
        HttpRequest request = requestSupplier.get();
        try {
            System.out.println("Sending " + request.method() + " to " + request.uri());
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                try {
                    if (bodyProcessor != null) {
                        return bodyProcessor.apply(new InputStreamReader(response.body(), StandardCharsets.UTF_8));
                    }
                    else {
                        return null;
                    }
                }
                catch(Exception e) {
                    throw new ResponseProcessingException(e.getMessage(), e);
                }
            }
            else {
                throw new ServerResponseException(statusCode, ClientHelper.readContents(response.body()));
            }
        }
        catch(IOException | InterruptedException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    //Read from file
    String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    //clear file
    void clearFile (String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        writer.print("");
        writer.close();
    }

    public boolean isLoggedIn() throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        return token1.length() != 0;
    }

    public String healthCheck() {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForHealthCheck()),
            ClientHelper::parseJsonStatus
        );
    }

    public String resetDatabase() {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForReset(), URL_ENCODED, HttpRequest.BodyPublishers.noBody()),
            ClientHelper::parseJsonStatus
        );
    }
//add throws IOexecption
    public void login(String username, String password) throws IOException {
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("username", username);
        formData.put("password", password);
     //   System.out.println(token);
        token = sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForLogin(), URL_ENCODED, ofUrlEncodedFormData(formData)),
            ClientHelper::parseJsonToken
        );
        // attach a file to FileWriter
        FileWriter fw=new FileWriter("softeng19bAPI.token");

        // read character wise from string and write
        // into FileWriter
        for (int i = 0; i < token.length(); i++)
            fw.write(token.charAt(i));
        //close the file
        fw.close();
    //    System.out.println(token);
    }
//add throws IOExceptions
    public void logout() throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForLogout(), URL_ENCODED, HttpRequest.BodyPublishers.noBody()),
            null
        );
        token = null;
        clearFile("softeng19bAPI.token");
    }

    //allaxa User->String
    public User addUser(String username, String email, String password, int quota) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("username", username);
        formData.put("email", email);
        formData.put("password", password);
        formData.put("requestsPerDayQuota", String.valueOf(quota));
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForAddUser(), URL_ENCODED, ofUrlEncodedFormData(formData)),
            ClientHelper::parseJsonUser

        );
    }

    public User updateUser(User updatedUser) throws IOException {
        //only email and/or quota can be updated
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("email", updatedUser.getEmail());
        formData.put("requestsPerDayQuota", String.valueOf(updatedUser.getRequestsPerDayQuota()));
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPutRequest(urlForUpdateUser(updatedUser.getUsername()), URL_ENCODED, ofUrlEncodedFormData(formData)),
            ClientHelper::parseJsonUser
        );
    }

    public List<User> getUser(String username) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForGetUser(username)),
            ClientHelper::getUser
        );
    }

    public ImportResult importFile(String dataSet, Path dataFilePath) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        String boundary = new BigInteger(256, new Random()).toString();
        Map<String, Object> formData = Map.of("file", dataFilePath);
        HttpRequest.BodyPublisher bodyPublisher = ofMultipartFormData(formData, boundary);
        String contentType = MULTIPART_FORM_DATA + ";boundary=" + boundary;
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForImport(dataSet), contentType, bodyPublisher),
            ClientHelper::parseJsonImportResult
        );
    }


    public List<ATLRecordForSpecificDay> getActualTotalLoad(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualDataLoad(areaName, resolutionCode, date, format)),
            format::consumeActualTotalLoadRecordsForSpecificDay
        );
    }

    public List<ATLRecordForSpecificMonth> getActualTotalLoadMonth(String areaName,
                                                              String resolutionCode, String month,
                                                              Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForActualDataLoadMonth(areaName, resolutionCode, month, format)),
                format::consumeActualTotalLoadRecordsForSpecificMonth
        );
    }

    public List<ATLRecordForSpecificYear> getActualTotalLoadYear(String areaName,
                                                                 String resolutionCode, String year,
                                                                 Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForActualDataLoadYear(areaName, resolutionCode, year, format)),
                format::consumeActualTotalLoadRecordsForSpecificYear
        );
    }

    public List<DARecordForSpecificDay> getDayAheadTotalLoad(String areaName,
                                                             String resolutionCode,
                                                             LocalDate date,
                                                             Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForDayAheadDataLoad(areaName, resolutionCode, date, format)),
                format::consumeDayAheadTotalLoadForecastRecordsForSpecificDay
        );
    }

    public List<DARecordForSpecificMonth> getDayAheadTotalLoadMonth(String areaName,
                                                                   String resolutionCode, String month,
                                                                   Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForDayAheadDataLoadMonth(areaName, resolutionCode, month, format)),
                format::consumeDayAheadTotalLoadForecastRecordsForSpecificMonth
        );
    }

    public List<DARecordForSpecificYear> getDayAheadTotalLoadYear(String areaName,
                                                                 String resolutionCode, String year,
                                                                 Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForDayAheadDataLoadYear(areaName, resolutionCode, year, format)),
                format::consumeDayAheadTotalLoadForecastRecordsForSpecificYear
        );
    }

    public List<AGPTRecordForSpecificDay> getAGPT(String areaName, String productionType,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForAGPT(areaName, productionType, resolutionCode, date, format)),
                format::consumeAggregatedGenerationPerTypeRecordsForSpecificDay
        );
    }

    public List<AGPTRecordForSpecificMonth> getAGPTMonth(String areaName, String productionType,
                                                                   String resolutionCode, String month,
                                                                   Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForAGPTMonth(areaName,productionType, resolutionCode, month, format)),
                format::consumeAggregatedGenerationPerTypeRecordsForSpecificMonth
        );
    }

    public List<AGPTRecordForSpecificYear> getAGPTYear(String areaName, String productionType,
                                                                 String resolutionCode, String year,
                                                                 Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForAGPTYear(areaName, productionType, resolutionCode, year, format)),
                format::consumeAggregatedGenerationPerTypeRecordsForSpecificYear
        );
    }

    public List<AVSFRecordForSpecificDay> getAVSF(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForAVSF(areaName, resolutionCode, date, format)),
                format::consumeActualVsForecastRecordsForSpecificDay
        );
    }

    public List<AVSFRecordForSpecificMonth> getAVSFMonth(String areaName,
                                                                   String resolutionCode, String month,
                                                                   Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForAVSFMonth(areaName, resolutionCode, month, format)),
                format::consumeActualVsForecastRecordsForSpecificMonth
        );
    }

    public List<AVSFRecordForSpecificYear> getAVSFYear(String areaName,
                                                                 String resolutionCode, String year,
                                                                 Format format) throws IOException {
        String token1 = readFile("softeng19bAPI.token");
        if (token1.length()!=0 ) {
            token = token1.substring(0,token1.length()-1);
        }
        else {
            token = token1.substring(0,token1.length());
        }
        return sendRequestAndParseResponseBodyAsUTF8Text(
                () -> newGetRequest(urlForAVSFYear(areaName, resolutionCode, year, format)),
                format::consumeActualVsForecastRecordsForSpecificYear
        );
    }


    //Helper method to create a new http client that can tolerate self-signed or improper ssl certificates
    private static HttpClient newHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        return HttpClient.newBuilder().sslContext(sslContext).build();
    }

    private static TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
        }
    };

    private static HttpRequest.BodyPublisher ofUrlEncodedFormData(Map<String, String> data) {
        return HttpRequest.BodyPublishers.ofString(ClientHelper.encode(data));
    }

    private static HttpRequest.BodyPublisher ofMultipartFormData(Map<String, Object> data, String boundary)
            throws IOException {
        var byteArrays = new ArrayList<byte[]>();
        String separator = "--" + boundary + "\r\nContent-Disposition: form-data; name=";
        byte[] separatorBytes = separator.getBytes(StandardCharsets.UTF_8);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            byteArrays.add(separatorBytes);

            if (entry.getValue() instanceof Path) {
                var path = (Path) entry.getValue();
                String mimeType = Files.probeContentType(path);
                byteArrays.add(("\"" + entry.getKey() + "\"; filename=\"" + path.getFileName()
                        + "\"\r\nContent-Type: " + mimeType + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                byteArrays.add(Base64.getMimeEncoder().encode(Files.readAllBytes(path)));
                byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));
            } else {
                byteArrays.add(("\"" + entry.getKey() + "\"\r\n\r\n" + entry.getValue() + "\r\n")
                        .getBytes(StandardCharsets.UTF_8));
            }
        }
        byteArrays.add(("--" + boundary + "--").getBytes(StandardCharsets.UTF_8));
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

}
