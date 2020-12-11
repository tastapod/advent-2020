package day9

fun firstInvalidXmasNumber(input: List<Long>, windowSize: Int) =
    input.drop(windowSize).zip(input.windowed(windowSize, 1)).find { (value, window) ->
        day1.findAddends(value, window, 2).isEmpty()
    }!!.first
