//package br.com.erudio.controller
//
//import br.com.erudio.exceptions.UnsupportedMathOperationException
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//import java.util.concurrent.atomic.AtomicLong
//
////@RestController
//class MathController2 {
//
//    val counter: AtomicLong = AtomicLong();
//
//    @RequestMapping(value=["/sum/{numberOne}/{numberTwo}"])
//    fun sum(@PathVariable(value="numberOne") numberOne: String?,
//            @PathVariable(value="numberTwo") numberTwo: String?
//    ): Double {
//        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
//            throw UnsupportedMathOperationException("Please set a numeric value!");
//        }
//        return convertToDouble(numberOne) + convertToDouble(numberTwo);
//    }
//
//
//    @RequestMapping(value=["/subtraction/{numberOne}/{numberTwo}"])
//    fun subtraction(@PathVariable(value="numberOne") numberOne: String?,
//                    @PathVariable(value="numberTwo") numberTwo: String?
//    ): Double {
//        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
//            throw UnsupportedMathOperationException("Please set a numeric value!");
//        }
//        return convertToDouble(numberOne) - convertToDouble(numberTwo);
//    }
//
//    @RequestMapping(value=["/multiplication/{numberOne}/{numberTwo}"])
//    fun multiplication(@PathVariable(value="numberOne") numberOne: String?,
//                       @PathVariable(value="numberTwo") numberTwo: String?
//    ): Double {
//        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
//            throw UnsupportedMathOperationException("Please set a numeric value!");
//        }
//        return convertToDouble(numberOne) * convertToDouble(numberTwo);
//    }
//
//    @RequestMapping(value=["/division/{numberOne}/{numberTwo}"])
//    fun division(@PathVariable(value="numberOne") numberOne: String?,
//                 @PathVariable(value="numberTwo") numberTwo: String?
//    ): Double {
//        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
//            throw UnsupportedMathOperationException("Please set a numeric value!");
//        }
//        return convertToDouble(numberOne) / convertToDouble(numberTwo);
//    }
//
//    @RequestMapping(value=["/mean/{numberOne}/{numberTwo}"])
//    fun mean(@PathVariable(value="numberOne") numberOne: String?,
//             @PathVariable(value="numberTwo") numberTwo: String?
//    ): Double {
//        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
//            throw UnsupportedMathOperationException("Please set a numeric value!");
//        }
//        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
//    }
//
//    @RequestMapping(value=["/squareRoot/{number}"])
//    fun squareRoot(@PathVariable(value="number") number: String?
//    ): Double {
//        if (!isNumeric(number)) {
//            throw UnsupportedMathOperationException("Please set a numeric value!");
//        }
//        return Math.sqrt(convertToDouble(number));
//    }
//
//
//
//
//
//
//
//}