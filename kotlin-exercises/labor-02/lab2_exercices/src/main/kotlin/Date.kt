import java.util.Calendar

data class Date (var year:Int = Calendar.YEAR,
                 var month:Int = Calendar.MONTH,
                 var day: Int = Calendar.DAY_OF_MONTH) : Comparable<Date>{//: Comparable<Date>


//    init {
//        var year: Int
//        var month: Int
//        var day: Int
//    }

    override fun compareTo(other: Date): Int {
        if (this.year != other.year)
            return this.year-other.year

        if (this.month != other.month) //year are the same
            return this.month-other.month

        return this.day-other.day   //year and month are the same
    }
    override fun toString(): String {
        return "Date(year=$year, month=$month, day=$day)"
    }

    companion object{
        val customDateComparator : Comparator<Date> = compareBy({it.year},{it.month}, {it.day})
    }

}