#Silverpop writer for Keboola Connection
KBC component for writing contact data using Silverpop XML API. It leverages Silerpop's asynchronous API allowing to update or add large amounts of contact data. 

It implements a subset of API commands supporting following actions:
- Add
- Update
- Opt Out

Therefore, it does not allow creation of new databases or new columns in an existing db. It allows adding and updating existing data in specified Engage database and contact list(s).

##Configuration
###Input configuration
The data that are to be imported to the Silverpop database are defined by Input mapping within KBC. User must simply define the source table containing the desired data in the *Table Input Mapping* section. See screenshot bellow.

![](https://raw.githubusercontent.com/davidesner/keboola-silverpop-writer/master/screens/config.png)

###Configuration parameters
**user** (REQ)

*Your Engage portal user login*

**#pass** (REQ)

*Your Engage portal password*

**api_url** (REQ)

*URL of the XML API endpoint*

**sftp_url** (REQ)

*URL of the Engage FTP server*

**list_date_format** (OPT) default: dd/mm/yyyy hh:mm a (e.g. 25/11/2015 10:05 AM)

*Used to specify the date format and date in source file*
	
**action**  (OPT) default: ADD_AND_UPDATE

*Defines the type of import you are performing. The following is
a list of valid values supported:*
- ADD_ONLY – only add new contacts to the database.
Ignore existing contacts when found in the source file.
- UPDATE_ONLY – only update the existing contacts in the
database. Ignore contacts who exist in the source file but
not in the database.
- ADD_AND_UPDATE – process all contacts in the source
file. If they already exist in the database, update their
values. If they do not exist, create a new record in the
database for the contact.
- OPT_OUT – opt out any contact in the source file who is
already in the database. Ignore contacts who exist in the
source file but not the database.

**list_id** (REQ)

*Unique ID of the database in the Engage system*
	
**encode_md5** (OPT) default:false

*If this element is set to true, email addresses in the Email column will be MD5 encoded.*
	
**contact_lists** (OPT) [...., ..., ...]

*Use the CONTACT_LISTS section if you want to specify one or more Contact Lists that all contacts will be added to in addition to the database. This section may be used with all actions except OPT_OUT.*
	
**mapping** (OPT) 

*Define which columns in the source file will be mapped to which columns in the database. If not defined, the names of the columns in the source table are expected to match those in Engage database. If any of the mandatory columns is not specified, or the source file does not contain all of them, the import process will fail. Check with the specification of your Engage db structure. If updating existing rows, only KEY columns must be specified, either in mapping or in source table. 
__NOTE:__ column names are NOT case sensitive.*

*__NOTE2:__ If the _action_ parameter is not _OPT_OUT_, columns related to the OPT OUT action - e.g. _OPTED OUT DATE_, _OPTED OUT DETAILS_, or _OPTED OUT_ must not be included in the mapping. Otherwise, the import will fail.*
```
Ex.
{ „field1_source_name“:“field1_dest_name“,
„field2_source_name“:“field2_dest_name“, ...
}
```

###Sample configuration:
```json
{
			"user": "login",
			"#pass": "pass",
			"api_url": "https://api[].silverpop.com/XMLAPI",
			"sftp_url": "transfer[].silverpop.com",
			"list_id": "15589048",
			"mapping": {
				"Email": "Email",
				"Email_Type": "Email Type",
				"Opt_In_Date": "Opt In Date",
				"addressline1": "addressline1"
			}
}
```
