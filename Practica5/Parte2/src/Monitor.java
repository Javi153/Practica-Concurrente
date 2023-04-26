public interface Monitor {
    public void requestRead();
    public void requestWrite();
    public void releaseRead();
    public void releaseWrite();
}
