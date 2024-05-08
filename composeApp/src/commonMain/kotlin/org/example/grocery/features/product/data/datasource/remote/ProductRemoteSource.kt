package org.example.grocery.features.product.data.datasource.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.grocery.features.product.data.datasource.ProductDataSource
import org.example.grocery.features.product.data.dto.ProductDto

class ProductRemoteSource : ProductDataSource {
    
    companion object {
        const val BASEURL = "https://fakestoreapi.com"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        defaultRequest {
           url(BASEURL)
        }
    }

    override suspend fun GetAll(limit: Int): Result<List<ProductDto>> {
        return try {
            val url = "/products" + if (limit > 0) "?limit=$limit" else ""
            Result.success(httpClient.get(url).body())
        } catch(ex: ClientRequestException) {
            println("Error: ${ex.response.status.description}")
            Result.failure(ex)
        }catch (ex: ResponseException) {
            println("Error: ${ex.response.status.description}")
            Result.failure(ex)
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }

    override suspend fun FindById(id: Int): Result<ProductDto> {
        return try {
            Result.success(httpClient.get("/products/$id").body())
        } catch (ex: Exception){
            Result.failure(ex)
        }
    }

}
