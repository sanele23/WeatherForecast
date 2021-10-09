package prove03;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class WeatherConditions {
        private Integer id;
        private String name;

        @SerializedName("main")
        private Map<String, Float> measurements;

        // Getter and Setter
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Map<String, Float> getMeasurements() { return measurements; }

        public String toString() {
            return "id: " + id + " name=" + name + " measurements: " + measurements;
        }
    }

    // Set variables


