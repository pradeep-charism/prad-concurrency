package practices.visitors;

public interface Website {

    int registerVisitors(String country);

    void get(String country, int id);

    void put(String country, int id);
}
