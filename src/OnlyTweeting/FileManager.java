package OnlyTweeting;

import twitter4j.auth.AccessToken;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Masaki on 2017/09/17.
 */
public class FileManager {

    /*
    欲しいメソッド
    ・所定のアクセストークンが存在するかを教えてくれるメソッド。
    ・アクセストークンを書き込むメソッド
    ・アクセストークンを取得するメソッド
     */

    private static void init() throws IOException {
        if (!Files.isDirectory(Paths.get(Settings.dir))) {
            Files.createDirectory(Paths.get(Settings.dir));
        }

        if (!Files.exists(Paths.get(Settings.dir, Settings.fileName))) {
            Files.createFile(Paths.get(Settings.dir, Settings.fileName));
        }
    }

    public static boolean hasAccessToken() {
        try {
            return Files.exists(Paths.get(Settings.dir, Settings.fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public static void writeAccessToken(AccessToken accessToken) {
        List<String> list = new ArrayList<>();
        list.add(accessToken.getToken());
        list.add(accessToken.getTokenSecret());
        try {
            init();
            Files.write(Paths.get(Settings.dir, Settings.fileName), list, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAccessToken() {
        try {
            init();
            return Files.readAllLines(Paths.get(Settings.dir, Settings.fileName), Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
