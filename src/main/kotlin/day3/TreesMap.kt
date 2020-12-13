package day3

class TreesMap(mapBlock: String) {
    private val mapLines: List<String> = mapBlock.lines()
    private val width = mapLines[0].length
    private val height = mapLines.size

    fun countTrees(right: Int, down: Int): Int {
        tailrec fun countTrees(x: Int, y: Int, count: Int): Int =
            if (y >= height - 1) count else countTrees(
                x + right,
                y + down,
                count + treeAt(x + right, y + down)
            )
        return countTrees(0, 0, 0)
    }

    private fun treeAt(x: Int, y: Int) = if (mapLines[y][x % width] == '#') 1 else 0
}
