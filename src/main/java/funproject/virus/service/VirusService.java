package funproject.virus.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import funproject.virus.model.VirusInfo;

@Service
public class VirusService {
    
    private static String VIRUS_DATA_SERVICE = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";

    private List<VirusInfo> allStats = new ArrayList<>();


    public List<VirusInfo> getAllStats() {
        return this.allStats;
    }

    public void setAllStats(List<VirusInfo> allStats) {
        this.allStats = allStats;
    }


    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException{
        List<VirusInfo> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_SERVICE)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            VirusInfo virusInfo = new VirusInfo();
            virusInfo.setCity(record.get("Admin2"));
            virusInfo.setState(record.get("Province_State"));
            virusInfo.setTotalCases(Integer.parseInt(record.get(record.size() - 1)));

            newStats.add(virusInfo);
        }
        this.allStats = newStats;
    }
}
