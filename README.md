# yagol - Yet Another Game of Life

#### TL;DR:
This is a _tiny_ demo around Android development. 
Involved in this repo:
- MVP architecture
- Custom View
- Android Design library (CoordinatorLayout and AppBarLayout)
- JUnit4 / Robolectric3 / Mockito
- RxJava

----
The main purpose purpose of this repo is to put in practise some Android dev techniques and libraries useful to develop apps.
Moreover I took a chance to implement the [Conway's_Game_of_Life](https://en.wikipedia.org/wiki/Conway's_Game_of_Life).

![demo](/../art/art/yagol-v0.0.2-main-demo.gif?raw=true)

According to _wikipedia_
>The "game" is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. 
>One interacts with the Game of Life by creating an initial configuration and observing how it evolves or, for advanced players, by creating patterns with particular properties.
######Rules
The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:

> * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
> * Any live cell with two or three live neighbours lives on to the next generation.
> * Any live cell with more than three live neighbours dies, as if by overcrowding.
> * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

-----
#### well known patterns
##### "Acorn"
![demo](/../art/art/yagol-v0.0.3-demo-acorn.gif?raw=true)


-----
##### TODO
- [ ] Fully decouple the algorithm computation from the ui-thread.
- [ ] Add Integration tests.


-----
##### Disclaimer: 
The algorithm itself is not optimized (as it wasn't really my target), check [conwaylife.com](http://www.conwaylife.com/) for ultra-fast implementations.
So run it only if you have a snapdragon810 and your phone is fully charged :)




