# file-storage
Rest API for pdf files

## Notes to start:
1. Change 'root' in <a href='https://github.com/ErasmusProjectFTN/file-storage/blob/master/src/main/resources/application.conf'> configuration file</a> to be storage in your local file system.
2. Start <a href='https://github.com/ErasmusProjectFTN/file-storage/blob/master/src/main/java/com/ErasmusProject/app/FileStoreServiceApplication.java'> App </a>

* Every new user must initialize personal storage calling <code>initialize</code> (host:port/storage/user with parameter userID)
