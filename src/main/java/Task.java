abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[ %s ] %s", getStatusIcon(), description);
    }

    public void mark() {
        this.setStatus(true);
        System.out.println("Great work Dokutah! I have marked this task as done for you:");
        System.out.println("   " + this.toString());
    }

    public void unmark() {
        this.setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("   " + this.toString());
    }

    public abstract String getType();
}