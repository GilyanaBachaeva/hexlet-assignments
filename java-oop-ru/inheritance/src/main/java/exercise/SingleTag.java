package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    private String tagName;
    private Map<String, String> attributes;

    public SingleTag(String tagName, Map<String, String> attributes) {
        super();
        if (tagName == null || attributes == null) {
            throw new IllegalArgumentException("Tag name and attributes cannot be null");
        }
        this.tagName = tagName;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tagName);

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }

        sb.append(">");
        return sb.toString();
    }
}
// END
