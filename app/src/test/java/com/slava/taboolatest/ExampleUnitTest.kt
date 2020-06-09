package com.slava.taboolatest

import com.slava.taboolatest.articles.entities.BaseDataModel
import com.slava.taboolatest.articles.entities.DataType
import com.slava.taboolatest.entities.Result
import com.slava.taboolatest.network.entities.ArticleServerModel
import com.slava.taboolatest.persistence.ArticleDBEntity
import com.slava.taboolatest.repositories.ArticlesRepository
import com.slava.taboolatest.taboola.TaboolaMediaAssembler
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testRepositoryFetchingFromRemoteApi() {
        val newsDBItem = ArticleDBEntity(
            id = 1,
            name = "From DB",
            description = "description",
            thumbnail = "imageUrl"
        )
        val articleServerModel =
            ArticleServerModel(
                name = "From DB",
                description = "description",
                thumbnail = "imageUrl"
            )
        val emptyArray: List<ArticleDBEntity> = ArrayList()
        val emptyNetworkArray: List<ArticleServerModel> = arrayListOf(articleServerModel)
        val dbArray: List<ArticleDBEntity> = arrayListOf(newsDBItem)
        val firstValue = Result(emptyNetworkArray)
        val firstTestValue = Result(emptyArray)
        val secondValue = Result(dbArray)

        val mockNetworkHandler = MockNetworkHandler(firstValue)
        val mockNewsDao = MockDaoHandler(secondValue, true)
        val newsRepository = ArticlesRepository(mockNetworkHandler, mockNewsDao)
        val testObserver = TestObserver<Result<List<ArticleDBEntity>>>()

        newsRepository.getData().subscribe(testObserver)

        val streamValues: ArrayList<Result<List<ArticleDBEntity>>> = ArrayList()
        streamValues.add(firstTestValue)
        streamValues.add(secondValue)
        testObserver.assertNoErrors().assertValueSequence(streamValues)
    }


    @Test
    fun testRepositoryFetchingFromRemoteDB() {
        val newsDBItem = ArticleDBEntity(
            id = 1,
            name = "From DB",
            description = "description",
            thumbnail = "imageUrl"
        )
        val emptyNetworkArray: List<ArticleServerModel> = ArrayList()
        val dbArray: List<ArticleDBEntity> = arrayListOf(newsDBItem)
        val firstValue = Result(emptyNetworkArray)
        val secondValue = Result(dbArray)

        val mockNetworkHandler = MockNetworkHandler(firstValue)
        val mockNewsDao = MockDaoHandler(secondValue)
        val newsRepository = ArticlesRepository(mockNetworkHandler, mockNewsDao)
        val testObserver = TestObserver<Result<List<ArticleDBEntity>>>()

        newsRepository.getData().subscribe(testObserver)

        val streamValues: ArrayList<Result<List<ArticleDBEntity>>> = ArrayList()
        streamValues.add(secondValue)
        streamValues.add(secondValue)
        testObserver.assertNoErrors().assertValueSequence(streamValues)
    }

    @Test
    fun taboolaMediaAssemblerTest() {
        val taboolaMediaAssembler = TaboolaMediaAssembler()
        val data = ArrayList<BaseDataModel>()
        data.add(BaseDataModel(DataType.ARTICLE))
        data.add(BaseDataModel(DataType.ARTICLE))
        data.add(BaseDataModel(DataType.ARTICLE))
        taboolaMediaAssembler.addTaboolaWidgets(data)
        assertEquals(DataType.ARTICLE, data[0].type)
        assertEquals(DataType.ARTICLE, data[1].type)
        assertEquals(DataType.TABOOLA_WIDGET, data[2].type)
        assertEquals(DataType.ARTICLE, data[3].type)
        assertEquals(DataType.TABOOLA_FEED, data[4].type)
    }

    @Test
    fun taboolaMediaAssemblerWithEmptyArticlesListTest() {
        val taboolaMediaAssembler = TaboolaMediaAssembler()
        val data = ArrayList<BaseDataModel>()
        taboolaMediaAssembler.addTaboolaWidgets(data)
        assertEquals(DataType.TABOOLA_FEED, data[0].type)
    }
}
