package advanced.io_example

class Main {
    static void main(String[] args) {
        // Read numbers from files and store them in List
        List<Integer> allNumbers = readAllNumbers()
        println allNumbers

        // Find the highest number
        Integer maxNum = allNumbers.max()
        assert maxNum == 2044
        println "max number in list: " + maxNum

        // Create the sum of all numbers
        Integer sum = allNumbers.sum()
        println sum
    }

    private static List<Integer> readAllNumbers() {
        File dir = new File("resources\\example")
        List<Integer> allNumbers = []
        dir.eachFile { file ->
            file.eachLine { line ->
                if (line.isNumber())
                    allNumbers << line.toInteger()
            }
        }


//        return allNumbers
        allNumbers
    }
}
