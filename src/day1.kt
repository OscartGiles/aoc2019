import java.io.File
import kotlin.math.max

fun readInputs(fileName: String): List<String>{
     return File(fileName).bufferedReader().readLines()
}

fun requiredFuel(mass: Int): Int{
//    toInt rounds down
    return (mass / 3.0).toInt() - 2
}

tailrec fun requiredFuelForFuel(fuelMass: Int, totalAddFuel: Int = 0): Int{

    val additionFuel: Int = requiredFuel(fuelMass) //Type defs not required
    if (additionFuel <= 0){
        return totalAddFuel
    }
    else{
        return requiredFuelForFuel(additionFuel, totalAddFuel + additionFuel)
    }

}
fun main() {

//    Assert that the output is correct (-ea must be given as an arg to runtine)
    assert(requiredFuel(100756) == 33583)
    assert(requiredFuelForFuel(100756) == 50346)

//    Part 1
//    Calculate total fuel required
    val inputs: List<Int> = readInputs("inputs/day1.txt").map{it.toInt()}
    val fuel: Int = inputs
        .map{requiredFuel(it)}
        .sum()

    println("The fuel required for the modules is  $fuel")

//    Part 2
    val totalFuelAccountingForFuel: Int = inputs
        .map{requiredFuelForFuel(it)}
        .sum()

    println("The total fuel required accounting for fuel is $totalFuelAccountingForFuel")



}