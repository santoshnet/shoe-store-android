package com.frontendsource.shoestore.repositories

import com.frontendsource.shoestore.data.local.RoomDao
import com.frontendsource.shoestore.models.Advertisement
import com.frontendsource.shoestore.models.Manufacturer
import com.frontendsource.shoestore.sealed.DataResponse
import com.frontendsource.shoestore.sealed.Error
import com.frontendsource.shoestore.utils.getStructuredManufacturers
import javax.inject.Inject

class BrandsRepository @Inject constructor(
    private val dao: RoomDao,
) {
    suspend fun getBrandsAdvertisements(): DataResponse<List<Advertisement>> {
        /** First we should check the local storage */
        dao.getAdvertisements().let {
            return if (it.isNotEmpty()) {
                DataResponse.Success(data = it)
            } else {
                /** Now we should fetch from the remote server */
                DataResponse.Error(error = Error.Empty)
            }
        }
    }

    suspend fun getBrandsWithProducts(): DataResponse<List<Manufacturer>> {
        /** First we should check the local storage */
        dao.getManufacturersWithProducts().getStructuredManufacturers().let {
            return if (it.isNotEmpty()) {
                DataResponse.Success(data = it)
            } else {
                /** Now we should fetch from the remote server */
                DataResponse.Error(error = Error.Empty)
            }
        }
    }
}

