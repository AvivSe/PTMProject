package game_server_interface;

/**
 * Aviv Segal 2018/17
 * Must implements any kind of Instructions (to goal).
 * It's actually converts the way to goal to something we can work with.
 * for example: PgInstructionsPgInstructions extends ArrayList of strings, helping convert the from backtrace to actual using (vectors to goal).
 * another example - the MazeInstructions convert the Grid positions to steps to goal (Strings ["RIGHT","LEFT"...])
 */
public interface Instructions { }
