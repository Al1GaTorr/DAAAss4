package utils;
import org.example.Graph;
import org.json.*;
import java.io.*;
import java.nio.file.*;

public class GraphLoader {
    public static Graph loadFromJson(String path) throws IOException {
        String json = Files.readString(Paths.get(path));
        JSONObject obj = new JSONObject(json);
        int n = obj.getInt("vertices");
        Graph g = new Graph(n);

        JSONArray edges = obj.getJSONArray("edges");
        for (int i = 0; i < edges.length(); i++) {
            JSONObject e = edges.getJSONObject(i);
            g.addEdge(e.getInt("u"), e.getInt("v"), e.getInt("w"));
        }
        return g;
    }
}
