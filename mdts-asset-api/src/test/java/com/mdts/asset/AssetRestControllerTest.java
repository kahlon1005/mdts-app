package com.mdts.asset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.mdts.asset.model.Asset;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({ "/asset_schema.sql", "/import_asset.sql" })
public class AssetRestControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void getAssetTest() throws Exception {

		String url = "http://localhost:" + port + "/api/v1/asset/91";

		Asset asset = testRestTemplate.getForObject(url, Asset.class);

		assertEquals("Monitor", asset.getName());
		assertEquals("COMPUTER", asset.getType());
		assertEquals("Monitor with Mike", asset.getDescription());
		assertNotNull(asset);

	}

	@Test
	public void postAssetTest() {
		String url = "http://localhost:" + port + "/api/v1/asset";
		Asset asset = Asset.builder()
				.name("iPhone")
				.type("phone")
				.description("iphone 13 pro")
				.build();

		Asset result = testRestTemplate.postForObject(url, asset, Asset.class);
		assertThat(result.getId()).isEqualTo(1);
	}

	@Test
	public void putAssetTest_whenAssetIdisNull_thenReturnBadRequest() throws URISyntaxException {
		String url = "http://localhost:" + port + "/api/v1/asset";

		Asset asset = Asset.builder()
				.name("iPhone")
				.type("phone")
				.description("iphone 13 pro")
				.build();

		// put
		testRestTemplate.put(url, asset);

		// exchange
		URI uri = new URI(url);
		RequestEntity<Asset> requestEntity = RequestEntity.put(uri).body(asset);

		ResponseEntity<Asset> result = testRestTemplate.exchange(requestEntity, Asset.class);

		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

}
