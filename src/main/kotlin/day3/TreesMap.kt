package day3

class TreesMap(mapBlock: String) {
    private val mapLines: List<String> = mapBlock.lines()
    private val width = mapLines[0].length
    private val height = mapLines.size

    fun countTrees(right: Int, down: Int): Int {
        var x = 0
        var y = 0
        var count = 0

        do {
            x += right
            y += down
            if (treeAt(x, y)) {
                count++
            }
        } while (y < height - 1)
        return count
    }

    private fun treeAt(x: Int, y: Int): Boolean {
        return mapLines[y][x % width] == '#'
    }
}
