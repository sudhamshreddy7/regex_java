# Regex Matcher in Java  

This program is a custom implementation of a regular expression (regex) matcher in Java. It demonstrates basic pattern matching functionalities, including special character handling, quantifiers, and pattern groups. Below is the detailed overview of the implementation, usage, and key features.

---

## Features  

1. **Pattern Matching**  
   - Supports matching specific characters (`a`, `b`, etc.) and special patterns like `\w` (word characters) and `\d` (digits).  
   - Includes quantifiers like `+` (one or more) and `?` (zero or one).  
   - Handles anchors like `^` (start of string) and `$` (end of string).  
   - Supports character groups (e.g., `[abc]` for positive matching and `[^abc]` for negative matching).  

2. **Multifunctional Matcher Methods**  
   - `matchPattern()`: The main method for interpreting and matching patterns.  
   - Helper functions: `matchCapitalLetters`, `matchSmallerLetters`, `computeDot`, `positiveCharacterGroups`, etc., are used for specific matching logic.

3. **Error Handling**  
   - Gracefully handles invalid expressions and unrecognized patterns with clear runtime exceptions.

4. **Custom Regex-like Features**  
   - Combines Java logic to emulate regex functionalities without using Java's built-in `Pattern` or `Matcher` classes.  

---

## Code Structure  

### Key Methods  

1. **`matchPattern(String inputLine, String pattern)`**  
   - Entry point for pattern matching.  
   - Determines which subroutine to call based on the pattern's content.  

2. **Helper Methods**  
   - `matchCapitalLetters`: Checks for uppercase letters in a string.  
   - `matchSmallerLetters`: Checks for lowercase letters in a string.  
   - `positiveCharacterGroups`: Matches patterns defined in brackets, e.g., `[a-z]`.  
   - `computeOneOrMore`: Implements the `+` quantifier.  
   - `computeZeroOrOne`: Implements the `?` quantifier.  
   - `computeDot`: Implements the `.` wildcard.  

---

## Usage  

1. **Run the Program**  
   Compile and run the program using `javac` and `java`:  
   ```bash
   javac Main.java  
   java Main -E <pattern>
   ```

2. **Input**  
   - The program expects the user to input a string via standard input.  
   - Example:  
     ```bash
     java Main -E "ca+t"
     ```
     Input: `caats`

3. **Output**  
   - Returns `0` for a successful match.  
   - Returns `1` if the pattern does not match.  
   - Example Output:  
     ```bash
     ./your_program.sh -E "ca+t"
     Logs from your program will appear here!
     ```

---

## Example Patterns and Behavior  

| **Pattern**      | **Description**                                     | **Example Match**   |  
|-------------------|-----------------------------------------------------|----------------------|  
| `\d`             | Matches any digit.                                  | `123`, `4`           |  
| `\w`             | Matches any word character (letters, digits, `_`).  | `abc123`, `hello_`   |  
| `[abc]`          | Matches any character in the group `a`, `b`, or `c`. | `a`, `b`            |  
| `[^abc]`         | Matches any character not in the group `a`, `b`, or `c`. | `d`, `z`          |  
| `ca+t`           | Matches `cat` followed by one or more `a`s.         | `caat`, `caaaat`     |  
| `ca?t`           | Matches `cat` with zero or one `a`.                 | `ct`, `cat`          |  

---

## Limitations  

1. The program may not handle nested or highly complex regex patterns.  
2. Limited support for some advanced regex features like lookaheads or backreferences.  
3. Custom logic can result in different behavior compared to standard regex engines.

---

## License  

This is a simple educational project. Feel free to use, modify, and distribute as needed.  

---

Let me know if you’d like further refinements or examples!
