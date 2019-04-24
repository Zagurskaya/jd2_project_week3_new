//package com.gmail.aperavoznikau.jd2w3.controller;
//
//import java.util.List;
//
//import com.gmail.aperavoznikau.jd2w3.service.ItemService;
//import com.gmail.aperavoznikau.jd2w3.service.model.ItemDTO;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.ui.ExtendedModelMap;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//
//import static java.util.Arrays.asList;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.collection.IsMapContaining.hasEntry;
//import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ItemControllerTest {
//
//    private MockMvc mockMvc;
//
//    private ItemController controller;
//
//    @Mock
//    private ItemService itemService;
//
//    @Mock
//    private BindingResult result;
//
//    private static final String READY_STATUS_PARAM = "READY";
//    private List<ItemDTO> items = asList(new ItemDTO(1L, "name", READY_STATUS_PARAM),
//            new ItemDTO(2L, "another name", "COMPLETED"));
//
//    @Before
//    public void init() {
//        controller = new ItemController(itemService);
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }
//
//    @Test
//    public void allItemsAreAddedToModelFoItemsView() {
//        Model model = new ExtendedModelMap();
//        when(itemService.getItems()).thenReturn(items);
//        String itemsView = controller.getItems(model);
//        assertThat(itemsView, equalTo("items"));
//        assertThat(model.asMap(), hasEntry("items", items));
//    }
//
//
//    @Test
//    public void shouldSeeAllItems() throws Exception {
//        when(itemService.getItems()).thenReturn(items);
//        this.mockMvc.perform(get("/items.html"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("items", equalTo(items)))
//                .andExpect(forwardedUrl("items"));
//    }
//
//    @Test
//    public void newItemIsAddedToModelFoItemView() {
//        String itemsView = controller.getItemView(new ItemDTO());
//        assertThat(itemsView, equalTo("item"));
//    }
//
//    @Test
//    public void itemIsAdded() {
//        ItemDTO itemDTO = new ItemDTO(1L, "name", READY_STATUS_PARAM);
//        when(result.hasErrors()).thenReturn(false);
//        String itemsView = controller.addItem(itemDTO, result);
//        assertThat(itemsView, equalTo("redirect:/items"));
//    }
//
//    @Test
//    public void itemIsNotAdded() {
//        ItemDTO itemDTO = new ItemDTO(1L, "name", READY_STATUS_PARAM);
//        when(result.hasErrors()).thenReturn(true);
//        String itemsView = controller.addItem(itemDTO, result);
//        assertThat(itemsView, equalTo("item"));
//    }
//
//
//}