import java.io.File

fun readInputsToList(fileName: String): List<Int>{
    return File(fileName).bufferedReader().readLine().split(',').map{it.toInt()}
}

tailrec fun executeInstruction(inputs: MutableList<Int>, i: Int = 0): MutableList<Int>{

    if (inputs[i] == 99) {
        return inputs
    } else if (inputs[i] == 1){
        inputs[inputs[i+3]] = inputs[inputs[i+1]] + inputs[inputs[i+2]]
        executeInstruction(inputs, i + 4)
    } else if (inputs[i] == 2) {
        inputs[inputs[i + 3]] = inputs[inputs[i + 1]] * inputs[inputs[i + 2]]
        executeInstruction(inputs, i + 4)
    }
    return inputs
}

fun findNounVerb(inputs: List<Int>): List<Int>{

    for (noun in 0..99){
        for (verb in 0..99){
            val inputsNew = inputs.toMutableList()
            inputsNew[1] = noun
            inputsNew[2] = verb
            val output = executeInstruction(inputsNew)[0]
            if (output == 19690720){
                return listOf<Int>(noun, verb)
            }
        }
    }
    return listOf(-9999, -9999)
}


fun main() {

    val inputs = readInputsToList("inputs/day2.txt")

    val inputs_p1 = inputs.toMutableList()

//    Part 1
//    Rest values
    inputs_p1[1] = 12
    inputs_p1[2] = 2

    val output = executeInstruction(inputs_p1)
    val answer = output[0]

    println("Input: $inputs")
    println("Output: $output")
    println("Answer: $answer")

//    Part 2
    val part2Out = findNounVerb(inputs)
    println("Part 2: The noun is ${part2Out[0]}. The verb is ${part2Out[1]}")
    println("Part 2: 100 * noun + verb = ${100 * part2Out[0] + part2Out[1]}")
}