# XSLTTest
Simple Java (Swing) tool for editing and testing XSLT

Built for personal usage, but free for every one to use. Launcher for the application is the static main in class Starter.
It is built as a simple tool, without any futher intentions. No action is taken into proper refactor or clean code.

1st tab: Source XML
2nd tab: XSLT source
3th tab: output XML

Switch between tabs: cmd 1/2/3 or the Win equivalent

Execute the compilation: Execute (cmd e)

You can store 'scenarios' as you might want to call them. Each save a version is saved as a collection of Serialized objects. 

Known issues: 
- 4th tab that is to show compilation logging (instead of stdout)
- No possibilities for including external XSLT resources (may be implemented once)


