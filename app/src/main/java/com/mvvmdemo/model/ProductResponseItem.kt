import android.os.Parcel
import android.os.Parcelable

data class ProductResponseItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readParcelable(Rating::class.java.classLoader) ?: Rating(0, 0.0),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(description)
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeDouble(price)
        parcel.writeParcelable(rating, flags)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductResponseItem> {
        override fun createFromParcel(parcel: Parcel): ProductResponseItem {
            return ProductResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<ProductResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}
