package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String tagName;
    protected Map<String, String> attributes;


    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    public Tag() {

    }

    public String getTagName() {
        return tagName;
    }


    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
// END
