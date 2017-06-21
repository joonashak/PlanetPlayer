# PlanetPlayer
Solar system simulator for fun and practice

This simulator allows one to become acquainted with orbital motion and
experiment how changes in the bodies' mass and velocity affect the
system. It's usable but still very much a work in progress. For
example, you may notice that the way the simulation is updated causes
a kind of lag and tends to slowly blow out the orbits. I had to slow
down Mercury ~8% from its true orbital velocity in order to achieve
some stability. For me, the main objective with this project was to
practice creating JavaFX UI's. Thus, it may well be that while an
interesting problem, I'll leave the actual simulation part as is.

Some development ideas include:
* Complete TODO's left in source...
* Pinch zoom for trackpads and touch screens.
* Visual scale.
* Time to apoapsis and perhaps some other information as well.
* Improve planet labeling.
* Add (a stylish) scroll bar to the control panel.
* Tune parameters for smoother & more accurate simulation.

## Installation

You will need at least JRE 8 (Java Runtime Environment) to run the
simulation. Developed with JDK 8.

You can either:
* Download the `.jar` file and run it by double-clicking or saying
`java -jar /path/to/PlanetPlayer.jar` in your console of choice.
* Compile from the source.

## Usage

Remove the Sun or change planet velocities to start with. Proper
in-program manual coming soon ;)

## History

TODO: Write history

## Credits

I got my inpiration for this project from
[Uni. Helsinki's programming MOOC](https://2017-ohjelmointi.github.io/part14/)
where in turn Robert Sedgewick and Kevin Wayne of Princeton are
credited for the original idea.

## License

This project is licensed under GNU AGPLv3. See 
[LICENSE](LICENSE) for details. The
[Ubuntu font](http://font.ubuntu.com/) and
[Open Iconic](https://github.com/iconic/open-iconic) icons are
licensed under their respective licenses. See
[FONT-LICENSE](FONT-LICENSE) and [ICON-LICENSE](ICON-LICENSE)
for details.
