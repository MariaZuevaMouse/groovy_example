package advanced.read_file

import advanced.collection.Person

class Main {
    static void main(String[] args) {
        Person johnDoe = new Person()

        // Read full contents of file
        File file = new File("resources/john-doe.txt")
        println file.getText("UTF-8")

        // Iterate over each line of file
        file.eachLine { line, no ->
            if (no == 1) {
                johnDoe.setFirstName(line)
            } else if (no == 2) {
                johnDoe.setLastName(line)
            } else if (no == 3) {
                johnDoe.setAge(line.toInteger())
            }else {
                throw new RuntimeException("A person file should only have 3 lines")
            }
        }
        println johnDoe.toString()
    }

}
