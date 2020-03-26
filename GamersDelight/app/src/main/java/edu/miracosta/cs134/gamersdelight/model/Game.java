package edu.miracosta.cs134.gamersdelight.model;

/**
 * The <code>Game</code> class maintains information about a video game,
 * including its id number, name, description, rating and image name.
 *
 * @author Michael Paulding
 * @version 1.0
 */
public class Game {

    //Member variables
    private String mName;
    private String mDescription;
    private float mRating;
    private String mImageName;

    /**
     * Creates a new <code>Game</code> from its name, description, rating and image name.
     *
     * @param description The game description
     * @param rating The game rating (out of 5.0)
     * @param imageName The image file name of the game
     */
    public Game(String name, String description, float rating, String imageName) {
        mName = name;
        mDescription = description;
        mRating = rating;
        mImageName = imageName;
    }

    public Game(){

    }
    /**
     * Creates a new <code>Game</code> from its name, description and rating.
     * The default image name controller.png is used.
     *
     * @param description The game description
     * @param rating The game rating (out of 5.0)
     */
    public Game(String name, String description, float rating) {
        this(name, description, rating, "controller.png");
    }

    /**
     * Gets the name of the <code>Game</code>.
     * @return The game name
     */
    public String getName() {
        return mName;
    }

    /**
     * Sets the name of the <code>Game</code>.
     * @param name The new game name.
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * Gets the description of the <code>Game</code>.
     * @return The task description
     */
    public String getDescription () {
        return mDescription;
    }

    /**
     * Sets the description of the <code>Game</code>.
     * @param desc The new task description
     */
    public void setDescription (String desc) {
        mDescription = desc;
    }

    /**
     * Gets the rating of the <code>Game</code>.
     * @return The rating (number of stars) of the game.
     */
    public float getRating() {
        return mRating;
    }

    /**
     * Sets the rating of the <code>Game</code>.
     * @param rating The rating (number of stars) of the game.
     */
    public void setRating(float rating) {
        mRating = rating;
    }

    /**
     * Gets the image file name of the <code>Game</code>.
     * @return The image file name (e.g. lol.png) of the game.
     */
    public String getImageName() {
        return mImageName;
    }

    /**
     * Sets the image file name of the <code>Game</code>.
     * @param imageName The image file name (e.g. lol.png) of the game.
     */
    public void setImageName(String imageName) {
        mImageName = imageName;
    }

    /**
     * A method for displaying a <code>Game</code> as a String in the form:
     *
     * Game{Name=League of Legends, Description=Multiplayer online battle arena game,
     * Rating=4.5, ImageName=lol.png}
     *
     * @return The formatted String
     */
    @Override
    public String toString() {
        return "Game{" +
                "Name='" + mName + '\'' +
                ", Description='" + mDescription + '\'' +
                ", Rating=" + mRating +
                ", ImageName='" + mImageName + '\'' +
                '}';
    }
}
