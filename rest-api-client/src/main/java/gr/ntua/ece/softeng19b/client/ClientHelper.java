package gr.ntua.ece.softeng19b.client;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.User;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientHelper {

    private static String parseJsonAndGetValueOfField(Reader r, String field) {
        Gson gson = new Gson();
        Map map = gson.fromJson(r, Map.class);
        return (String) map.get(field);
    }

    static String parseJsonStatus(Reader r) {
        return parseJsonAndGetValueOfField(r, "status");
    }

    static String parseJsonToken(Reader r) {
        return parseJsonAndGetValueOfField(r, "token");
    }

    static User parseJsonUser(Reader r) {
        return new Gson().fromJson(r, User.class);
    }

    static List<User> getUser(Reader r) {
        try (JsonReader jsonReader = new JsonReader(r)) {
            return readuser(jsonReader);
        } catch(IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static List<User> readuser(JsonReader reader)
            throws IOException {
        List<User> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(Readuser(reader));
        }
        reader.endArray();
        return result;
    }

    private static User Readuser(JsonReader reader)
            throws IOException {
        User rec = new User();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "username":
                    rec.setUsername(reader.nextString());
                    break;
                case "email":
                    rec.setEmail(reader.nextString());
                    break;
                case "requestsPerDayQuota":
                    rec.setRequestsPerDayQuota(reader.nextInt());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    static String parseString(Reader r){
     //   System.out.println(r.toString());
        return r.toString();
    }

    static ImportResult parseJsonImportResult(Reader r) {
        return new Gson().fromJson(r, ImportResult.class);
    }

    static String readContents(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).
                lines().collect(Collectors.joining("\n"));
    }

    public static String encode(Map<String, String> data) {
        var builder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return builder.toString();
    }
}
