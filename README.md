# Piranha
A Windows XP Minesweeper Bot

<!---------------------------------------------------------------------------------
| PPPPPPP  IIIII   RRRRRRR          A        NN      N   H       H        A       |
| P      P   I     R      R        A A       N N     N   H       H       A A      |
| P      P   I     R      R       A   A      N  N    N   H       H      A   A     |
| PPPPPPP    I     RRRRRRR       AAAAAAA     N   N   N   HHHHHHHHH     AAAAAAA    |
| P          I     R     R      A       A    N    N  N   H       H    A       A   |
| P          I     R      R    A         A   N     N N   H       H   A         A  |
| P        IIIII   R       R  A           A  N      NN   H       H  A           A |
---------------------------------------------------------------------------------->

Setup
-----------------------------------------------------------------------------------
Firstly, if you do not have Minesweeper for Windows XP, download it [here](http://www.minesweeper.info/downloads/WinmineXP.html).

To run this using Netbeans, simply open the project using Netbeans and proceed to the next step.
I have not yet created a batch file as a part of this project but one will be made in the near future.

Running It
-----------------------------------------------------------------------------------
Run the Piranha in **DEBUG MODE!!!** I have not yet nailed the play-until-win function in Piranha for Minesweeper
fields with widths over 9 boxes and if you do not, you may end up having to force close Piranha.

Tune the `pause()` method in the main class to multiply seconds to 1000.
If you are using anything bigger than a 9x9 minesweeper field, look at **Tuning for Larger Minesweeper Boxes** below.

Else, put Minesweeper in the upper-left hand corner of the screen (It does *not* need to be in an exact location) and run Piranha (Once again in Debug Mode).

While in **Debug Mode** you can quit a program using the shortcut `Shift` + `F5`.

Tuning for Larger Minesweeper Boxes
-----------------------------------------------------------------------------------
In the main class of Piranha, the variables that control how large to make the box array are `rowsX` and `columnsY` which are pretty self explanitory.

Tuning for A Faster Solve
-----------------------------------------------------------------------------------
To get an optimal solve, look in the main class of Piranha for the very last method called `pause()`.
Change the multiplier to `seconds` to anything you like. The smaller the number, the faster it will go.
`1000` is 1000 milliseconds which is equal to 1 second.
