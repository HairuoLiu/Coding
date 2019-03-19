
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 * @author Liu.3502
 * @created 2018-09-10 下午1:17
 */
public class LC535_EncodeandDecodeTinyURL {

    Map<Integer, String> map = new HashMap<>();
    String host = "http://tinyurl.com/";

    public String encode(String longUrl) {
        int key = longUrl.hashCode();
        map.put(key, longUrl);
        return host+key;
    }

    public String decode(String shortUrl) {
        int key = Integer.parseInt(shortUrl.replace(host,""));
        return map.get(key);
    }

}
