package com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.data

import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.listas.ListaItem
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.models.ProductoCatalogoItem
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.catalogo.ProductoCatalogoItem
import com.rominarodriguez.ncr1125_rodriguez_rivas_gleidi_romina.screens.listas.ProductoItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("listas.php")
    suspend fun obtenerListas(): List<ListaItem>


    @GET("catalogo.php")
    suspend fun obtenerProductosCatalogo(): List<ProductoCatalogoItem>


    @GET("detalle_lista.php")
    suspend fun obtenerProductos(
        @Query("lista_id") listaId: Int
    ): List<ProductoItem>

    companion object {
        private const val BASE_URL = "https://romina.alwaysdata.net/"

        val instance: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}