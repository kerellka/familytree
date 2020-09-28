# familytree
A family tree viewer.

You will need 2 text files to create a tree.
One will contain info of all family members. The other will contain info of all family ties.

## Creating a file with information about all family members
To add family member, use the following syntax:

`name id = { name, surname, patronymic, sex, birth_date, death_date }`

* **name** - name of the person
* **surname** - surname of the person
* **patronymic** - patronymic of the person
* **id** - sequence number (starting from 1)
* **sex** - sex of the person
* **birth_date** - date of person\`s birth, "day.month.year" format
* **death_date** - date of person\`s death, "day.month.year" format or "null", if still alive

## Creating a file with information about all family ties
To add family ties, use the following syntax:

`maxGen = num` 

`child -> parent`

`spouse1 - spouse2`

`brtoher/sister <-> sister/brother`


**Note: Each family ties file must begin with the header `maxGen = num`!**

* **"maxGen = num"** - oldest generation number
* **"->"** to tie child and parent
* **"-"** to tie one spouse to other
* **"<->"** to tie one sister/brother to other

**P.S. There is no GUI for file selection in this version. To display your tree, replace the file paths in `MainFrame.java` with yours.**
