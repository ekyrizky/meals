package com.ekyrizky.network

import com.ekyrizky.network.service.MealService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MealServiceTest : ApiAbstract<MealService>() {

    private lateinit var service: MealService

    @Before
    fun initService() {
        service = createService(MealService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun `fetch categories test`() = runTest {
        enqueueResponse("/CategoriesResponse.json")
        val response = service.fetchCategories()
        val resBody = requireNotNull((response as ApiResponse.Success).data)
        val category = resBody.categories?.get(0)

        assertThat(resBody.categories?.size, `is`(14))
        assertThat(category?.name, `is`("Beef"))
        assertThat(category?.image, `is`("https://www.themealdb.com/images/category/beef.png"))
        assertThat(category?.description, `is`("Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]"))
    }

    @Throws(IOException::class)
    @Test
    fun `fetch meal by category test`() = runTest {
        enqueueResponse("/MealsByCategoryResponse.json")
        val response = service.fetchMealsByCategory("beef")
        val resBody = requireNotNull((response as ApiResponse.Success).data)
        val meal = resBody.meals?.get(0)

        assertThat(resBody.meals?.size, `is`(46))
        assertThat(meal?.id, `is`("52874"))
        assertThat(meal?.name, `is`("Beef and Mustard Pie"))
        assertThat(meal?.image, `is`("https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg"))
    }

    @Throws(IOException::class)
    @Test
    fun `fetch meal test`() = runTest {
        enqueueResponse("/MealResponse.json")
        val response = service.fetchMeal("52878")
        val resBody = requireNotNull((response as ApiResponse.Success).data)
        val meal = resBody.meals?.get(0)

        assertThat(resBody.meals?.size, `is`(1))
        assertThat(meal?.id, `is`("52878"))
        assertThat(meal?.name, `is`("Beef and Oyster pie"))
        assertThat(meal?.image, `is`("https://www.themealdb.com/images/media/meals/wrssvt1511556563.jpg"))
        assertThat(meal?.origin, `is`("British"))
        assertThat(meal?.instructions, `is`("Season the beef cubes with salt and black pepper. Heat a tablespoon of oil in the frying pan and fry the meat over a high heat. Do this in three batches so that you don’t overcrowd the pan, transferring the meat to a large flameproof casserole dish once it is browned all over. Add extra oil if the pan seems dry.\r\nIn the same pan, add another tablespoon of oil and cook the shallots for 4-5 minutes, then add the garlic and fry for 30 seconds. Add the bacon and fry until slightly browned. Transfer the onion and bacon mixture to the casserole dish and add the herbs.\r\nPreheat the oven to 180C/350F/Gas 4.\r\nPour the stout into the frying pan and bring to the boil, stirring to lift any stuck-on browned bits from the bottom of the pan. Pour the stout over the beef in the casserole dish and add the stock. Cover the casserole and place it in the oven for 1½-2 hours, or until the beef is tender and the sauce is reduced.\r\nSkim off any surface fat, taste and add salt and pepper if necessary, then stir in the cornflour paste. Put the casserole dish on the hob – don’t forget that it will be hot – and simmer for 1-2 minutes, stirring, until thickened. Leave to cool.\r\nIncrease the oven to 200C/400F/Gas 6. To make the pastry, put the flour and salt in a very large bowl. Grate the butter and stir it into the flour in three batches. Gradually add 325ml/11fl oz cold water – you may not need it all – and stir with a round-bladed knife until the mixture just comes together. Knead the pastry lightly into a ball on a lightly floured surface and set aside 250g/9oz for the pie lid.\r\nRoll the rest of the pastry out until about 2cm/¾in larger than the dish you’re using. Line the dish with the pastry then pile in the filling, tucking the oysters in as well. Brush the edge of the pastry with beaten egg.\r\nRoll the remaining pastry until slightly larger than your dish and gently lift over the filling, pressing the edges firmly to seal, then trim with a sharp knife. Brush with beaten egg to glaze. Put the dish on a baking tray and bake for 25-30 minutes, or until the pastry is golden-brown and the filling is bubbling."))
    }
}