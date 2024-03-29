# Project5

![Project 5 UML](https://user-images.githubusercontent.com/47369140/57089571-55315200-6cca-11e9-8a16-933c80b7105e.jpg)


For this project we were asked to implement the Mesonet code that we’ve been working with for projects 1,2, and 3. This includes reading a file with station codes and determine their hamming distance. Specifically, we had the task of making a GUI that would show outputs related to previous projects.


The first thing that I did for the project was create a class that would handle all of the calculations such as the total hamming distance. The class was named hammingDist. The readFile method was also included in this this class. The code of the method in this class were very similar to past methods we had to write in past projects so I had no problems with this class.


The main class that I spend most of my time writing was the MesoFrame class. This class would be responsible for making the main JFrame as well as creating all the components and panels that will be placed inside of the JFrame. For the JFrame I decided to use a BorderLayout for the layout manager. This would allow me to set a panel to left side of the frame which would match the design of the sample program that was given to us. 


The main panel that I used that hold almost all of the components was the MesoPanel panel. This panel had a GridBagLayout. This allowed me to have more freedom of placing the componets that I made in the exact place that I wanted them to be. This was a little bit more complicated then using the other layout managers because I had to set the coordinates of all the components. 


I had to create a actionListsener for the JSlider that would print out the number value into the JTextField that is located to the right of it. To number value was determined by what number the JSlider was on.


For the “show station” button I had to make an actionListener that would print out the list of stations that had a distance matching the number selected in the JSlider. To do this the actionListener would call a protected ArrayList that was created in the HammingDist class. It would then iterate through the ArrayList. While iterating It would add a station to a String variable and then create a new line. Creating a new line would allow the ArrayList to appear as a vertical list instead of being one long line. I did have a bit of difficulty getting it to print out vertically because at first the stations were being printed in a horizontal line within square brackets


The next actionListener that I had to make is for the “Calc HD”. This button had the job of outputting the number of stations that had a hamming distance of one, two, three, or four from the station that is selected into a JTextField. To do this I had the actionListener print the created ints variables that I had that kept count of how many stations had a specific hamming distance (distOne, distTwo, distThree, distFour). I had to use the String.valueOf to convert the int into a string in order to print it out into the JTextField.


After that I created the Add Station button. The add station button has the responsibility of adding a new four code station to the ArrayList that holds the rest of the stations that were added from the readFile method. To do this, inside of the actionListener I created a new HammingDist object that uses the string that was entered in the textfield that corelates with the add station button. The actionListener also adds the string to the JComboBox.


The extra stuff that I did for the project was add a banner on the top of the frame that displayed “Hamming Distance”. This was done by just making a JLabel with that text in it and then I had to increase the size so it would appear bigger. The next thing that I did was display the logo of Mesonet on the right side of the frame. To do this I had to use a ImageIcon. Another component that I added was a JLabel that would be displayed when a new station was added. It appears on the bottom of the frame and says “Station Added!!”.


The most difficult part of this project was figuring out how you want to structure your program. Things like how many classes do I want, or how are all the member fields going to be connected between classes. Another difficult choice for the project was figuring out how to organize the JFrame. I took way more time then I wanted to pick the layout manager for the Jframe and the JPanels. It was very difficult to attempt to get my JFrame to look similar to the example program that we were given. 
