package com.rtuitlab.studo.server.general.ads

import com.rtuitlab.studo.server.Resource
import com.rtuitlab.studo.server.ResponseHandler
import com.rtuitlab.studo.server.general.ads.models.*
import java.lang.Exception

class AdsRepository(
    private val adsApi: AdsApi,
    private val responseHandler: ResponseHandler
) {
    suspend fun getAllAds(): Resource<List<CompactAd>> {
        return try {
            responseHandler.handleSuccess(adsApi.getAllAds())
        } catch (e: Exception) {
            val errorResource: Resource<List<CompactAd>> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                getAllAds()
            } else {
                errorResource
            }
        }
    }

    suspend fun getAd(adId: String): Resource<Ad> {
        return try {
            responseHandler.handleSuccess(adsApi.getAd(adId))
        } catch (e: Exception) {
            val errorResource: Resource<Ad> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                getAd(adId)
            } else {
                errorResource
            }
        }
    }

    suspend fun getUserAds(userId: String): Resource<List<CompactAd>> {
        return try {
            responseHandler.handleSuccess(adsApi.getUserAds(userId))
        } catch (e: Exception) {
            val errorResource: Resource<List<CompactAd>> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                getUserAds(userId)
            } else {
                errorResource
            }
        }
    }



    suspend fun createAd(
        name: String,
        description: String,
        shortDescription: String,
        beginTime: String,
        endTime: String,
        organizationId: String? = null
    ): Resource<Ad> {
        return try {
            responseHandler.handleSuccess(adsApi.createAd(CreateAdRequest(
                name, description, shortDescription, beginTime, endTime, organizationId
            )))
        } catch (e: Exception) {
            val errorResource: Resource<Ad> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                createAd(name, description, shortDescription, beginTime, endTime, organizationId)
            } else {
                errorResource
            }
        }
    }

    suspend fun editAd(
        id: String,
        name: String,
        description: String,
        shortDescription: String,
        beginTime: String,
        endTime: String
    ): Resource<Ad> {
        return try {
            responseHandler.handleSuccess(adsApi.editAd(EditAdRequest(
                id, name, description, shortDescription, beginTime, endTime
            )))
        } catch (e: Exception) {
            val errorResource: Resource<Ad> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                createAd(id, name, description, shortDescription, beginTime, endTime)
            } else {
                errorResource
            }
        }
    }

    suspend fun deleteAd(adId: String): Resource<Unit> {
        return try {
            responseHandler.handleSuccess(adsApi.deleteAd(adId))
        } catch (e: Exception) {
            val errorResource: Resource<Unit> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                deleteAd(adId)
            } else {
                errorResource
            }
        }
    }



    suspend fun getFavouritesAds(): Resource<List<CompactAd>> {
        return try {
            responseHandler.handleSuccess(adsApi.getFavouritesAds())
        } catch (e: Exception) {
            val errorResource: Resource<List<CompactAd>> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                getFavouritesAds()
            } else {
                errorResource
            }
        }
    }

    suspend fun addToFavourites(adId: String): Resource<Unit> {
        return try {
            responseHandler.handleSuccess(adsApi.addToFavourites(adId))
        } catch (e: Exception) {
            val errorResource: Resource<Unit> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                addToFavourites(adId)
            } else {
                errorResource
            }
        }
    }

    suspend fun removeFromFavourites(adId: String): Resource<Unit> {
        return try {
            responseHandler.handleSuccess(adsApi.removeFromFavourites(adId))
        } catch (e: Exception) {
            val errorResource: Resource<Unit> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                removeFromFavourites(adId)
            } else {
                errorResource
            }
        }
    }



    suspend fun createComment(adId: String, commentText: String): Resource<Comment> {
        return try {
            responseHandler.handleSuccess(adsApi.createComment(adId, CreateCommentRequest(commentText)))
        } catch (e: Exception) {
            val errorResource: Resource<Comment> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                createComment(adId, commentText)
            } else {
                errorResource
            }
        }
    }

    suspend fun deleteComment(adId: String, commentId: String): Resource<String> {
        return try {
            responseHandler.handleSuccess(adsApi.deleteComment(adId, commentId))
        } catch (e: Exception) {
            val errorResource: Resource<String> = responseHandler.handleException(e)
            if (errorResource.message == responseHandler.retryError) {
                deleteComment(adId, commentId)
            } else {
                errorResource
            }
        }
    }
}