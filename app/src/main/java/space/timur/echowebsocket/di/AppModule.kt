package space.timur.echowebsocket.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import space.timur.echowebsocket.common.WEBSOCKET_URL
import space.timur.echowebsocket.data.remote.WebSocketApi
import space.timur.echowebsocket.data.repository.WebSocketRepositoryImpl
import space.timur.echowebsocket.domain.repository.WebSocketRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRequest(): Request{
        return Request.Builder().url(WEBSOCKET_URL).build()
    }

    @Provides
    @Singleton
    fun provideWebSocketApi(client: OkHttpClient, request: Request): WebSocketApi{
        return WebSocketApi(client, request)
    }

    @Provides
    @Singleton
    fun provideWebSocketRepository(webSocketApi: WebSocketApi): WebSocketRepository{
        return WebSocketRepositoryImpl(webSocketApi)
    }

}
