package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerDTOPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    //public static final String BASE_URL = "http://localhost:8080";
    public static final String GET_BEER_PATH = "/api/v1/beer";
    private final RestTemplateBuilder restTemplateBuilder;

    @Override
    public Page<BeerDTO> listBeers(String beerName) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);
        if(beerName != null){
            //This will encode any parameter
            uriComponentsBuilder.queryParam("beerName", beerName);
        }
        ResponseEntity<BeerDTOPageImpl> response =
                restTemplate.getForEntity(uriComponentsBuilder.toUriString(), BeerDTOPageImpl.class);

        return response.getBody();

//        ResponseEntity<String> stringResponse =
//                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, String.class);

//        ResponseEntity<Map> mapResponse =
//                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, Map.class);
//
//        ResponseEntity<JsonNode> jsonResponse =
//                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, JsonNode.class);
//
//        jsonResponse.getBody().findPath("content")
//                .elements().forEachRemaining(jsonNode -> {
//                    System.out.println(jsonNode.get("beerName").asText());
//                });
//
//        System.out.println(stringResponse.getBody());


    }
}
