package edu.frogger.game;


public class Player {
    private int posX;
    private int posY;
    private int yIdx;
    private int lives;
    private boolean onLog = false;
    private String character;


    Player(String difficulty, int character) {
        this.posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        this.posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());
        this.yIdx = 15;

        if (difficulty.equals("Easy")) {
            this.lives = 3;
        } else if (difficulty.equals("Medium")) {
            this.lives = 2;
        } else {
            this.lives = 1;
        }

        if (character == 1) {
            this.character = "Billy";
        } else if (character == 2) {
            this.character = "Josh";
        } else {
            this.character = "Aurora";
        }
    }

    public void setOnLog(boolean onLog) {
        this.onLog = onLog;
    }

    public boolean getOnLog() {
        return onLog;
    }

    public void resetPos() {
        this.posX = TileMap.getBorderWidth() + (5 * TileMap.getTileWidth());
        this.posY = TileMap.getBorderHeight() + (15 * TileMap.getTileWidth());
        this.yIdx = 15;
        this.onLog = false;
        PointSystem.resetPoints();
    }

    public void setPosX(int posX) {
        if (onLog) {
            this.posX = posX;
        } else if (posX < TileMap.getBorderWidth() + (TileMap.getMapWidth()
                * TileMap.getTileWidth()) && posX >= 0) {
            this.posX = posX;
        }
    }


    public void setPosY(int posY) {
        if (posY < TileMap.getMapHeight() * TileMap.getTileWidth() + TileMap.getBorderHeight()
                && posY >= TileMap.getBorderHeight()) {
            if (this.posY < posY) {
                this.yIdx++;
            } else {
                this.yIdx--;
            }
            this.posY = posY;
        }
    }

    public void checkBadTile() {
        int tileValue = TileMap.getTile(0, yIdx);
        if (tileValue == 2 && !onLog) {
            lives--;
            resetPos();
        }
    }

    public void moveRight() {
        setPosX(posX + TileMap.getTileWidth());
    }

    public void moveLeft() {
        setPosX(posX - TileMap.getTileWidth());
    }

    public void moveDown() {
        setPosY(posY + TileMap.getTileWidth());
    }

    public void moveUp() {
        setPosY(posY - TileMap.getTileWidth());
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getLives() {
        return lives;
    }

    public int getYIdx() {
        return yIdx;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getCharacter() {
        return character;
    }

}
