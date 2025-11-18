public class Position {
    public int x;
    public int y;
    public Position p;
    public Position parent;
    public Position (int xcoor, int ycoor) {
        x = xcoor;
        y = ycoor;
    }

    public Boolean isEqual(Position p) {
        if (this.x == p.x && this.y == p.y) {
            return true;
        }
        return false;
    }

    public void setParent(Position par){
        this.parent = par;
    }
    public Position getParent(){
        return this.parent;
    }
}
