package co.com.inventory.inventoryservice.controllers;

import co.com.inventory.inventoryservice.models.ProductDto;
import co.com.inventory.inventoryservice.usecases.IInventoryGetUseCase;
import co.com.inventory.inventoryservice.usecases.IInventoryPatchUseCase;
import co.com.inventory.inventoryservice.usecases.IInventoryPostUseCase;
import co.com.inventory.inventoryservice.usecases.IInventoryPutUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(InventoryController.class)
class InventoryControllerMvcExampleTest {


    @Autowired
    private MockMvc mvc ;

    @MockBean
    IInventoryGetUseCase getUseCase ;

    @MockBean
    IInventoryPostUseCase postUseCase ;

    @MockBean
    IInventoryPutUseCase putUseCase ;

    @MockBean
    IInventoryPatchUseCase patchUseCase ;

    @DisplayName("Validar el codigo de respuesta es 404")
    @Test
    void validarStatusCode400(){

        List.of(ProductDto.builder()
                .id("abc")
                .name("un producto X")
                .build()) ;

        Mockito
                .when(getUseCase.read(Mockito.any()))
                .thenReturn(new ArrayList<>());

        try {
            mvc
                .perform(MockMvcRequestBuilders
                        .get("/inventory")
                        .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

       //  Mockito.verify(getUseCase).read(Mockito.any());

    }

    @DisplayName("Validar el codigo de respuesta es 200")
    @Test
    void validarStatusCode200(){



        Mockito
                .when(getUseCase.read(Mockito.any()))
                .thenReturn(List.of(ProductDto.builder()
                        .id("abc")
                        .name("un producto X")
                        .build()));

        try {
            mvc
                    .perform(MockMvcRequestBuilders
                            .get("/inventory?id=1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
            ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Mockito.verify(getUseCase).read(Mockito.any());

    }

}