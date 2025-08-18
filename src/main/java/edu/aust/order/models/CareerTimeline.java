package edu.aust.order.models;

public class CareerTimeline {

    public static void main(String[] args) {
        CareerTimeline career = new CareerTimeline();
        career.start();
    }

    void start() {
        year(2005, "Graduated from AUST");
        year(2005, "Part-time Lecturer @ AUST", "Lab Assistant @ NSU", duration(2));
        year(2008, "Software Engineer - Telecom (Warid, Airtel)", duration(3));
        year(2011, "Sr. Software Engineer @ IBM Bangladesh Ltd.", duration(5));
        year(2016, "Sr. Software Engineer @ Dhaka Bank Ltd.", duration(2));
        year(2018, "Sr. Software Engineer - Netherlands Govt. Projects "
                + "(Tax Office, ICT Ministry)", duration(5));
        year(2024, "Technical Architect / SSE / Coaching (Tax Office)", duration(2));
    }

    void year(int year, String... details) {
        System.out.printf("%d -> %s%n", year, String.join(" | ", details));
    }

    String duration(int years) {
        return "~" + years + " years";
    }
}
