package co.com.inventory.inventoryservice.usecases.impl;

import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.services.IInventoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InventoryGetUseCaseTest {

    @InjectMocks
    InventoryGetUseCase getService;

    @Mock
    IInventoryService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("validar parametros vacios de la operacion get ")
    @Test
    void readWhenEmptyParameters() {
        List<ProductDto> realResults = getService.read(new HashMap<>());
        List<ProductDto> expectedResults = Collections.EMPTY_LIST;
        assertEquals(expectedResults, realResults);
    }

    @DisplayName("validar resultados cuando el parametro es id ")
    @Test
    void readWhenParameterIsId() {

        List<ProductDto> products = new ArrayList<ProductDto>();

        String id = UUID.randomUUID().toString();

        products.add(ProductDto
                .builder()
                .id(id)
                .build());

        Mockito
                .when(service.getById(Mockito.any(String.class)))
                .thenReturn(products);

        Map<String, String> inputData = new HashMap<>();

        inputData.put("id", id);

        List<ProductDto> realResults = getService.read(inputData);

        List<ProductDto> expectedResults = Collections.EMPTY_LIST;

        assertAll(
                () -> {
                    assertNotNull(realResults);
                },
                () -> {
                    assertNotEquals(expectedResults, realResults);
                },
                () -> {
                    assertEquals(realResults.get(0), products.get(0));
                }
        );


    }

    @DisplayName("validar resultados cuando el parametro es name ")
    @Test
    void readWhenParameterIsName() {

        List<ProductDto> products = new ArrayList<ProductDto>();

        String id = UUID.randomUUID().toString();

        products.add(ProductDto
                .builder()
                .id(id)
                .build());

        id = UUID.randomUUID().toString();
        products.add(ProductDto
                .builder()
                .id(id)
                .build());

        Mockito
                .when(service.getByName(Mockito.any(String.class)))
                .thenReturn(products);

        Map<String, String> inputData = new HashMap<>();

        inputData.put("name", "testName");

        List<ProductDto> realResults = getService.read(inputData);

        List<ProductDto> expectedResults = Collections.EMPTY_LIST;

        assertAll(
                () -> {
                    assertNotNull(realResults, "Test non empty results");
                },
                () -> {
                    assertNotEquals(expectedResults, realResults, "Test non empty list");
                },
                () -> {
                    assertEquals(realResults.get(0), products.get(0), "Test valid results");
                },
                () -> {
                    assertEquals(realResults.get(1), products.get(1), "Test valid results");
                }
        );


    }


}