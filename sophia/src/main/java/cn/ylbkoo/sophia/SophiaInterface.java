package cn.ylbkoo.sophia;

public class SophiaInterface {
    static
    {
        try
        {
            System.loadLibrary("db_sophia");
        }
        catch(UnsatisfiedLinkError ule)
        {
            System.out.println("Cannot load library");
            System.out.println(ule);
        }
    }

    private volatile long sophiaEnvPtr;

    public SophiaInterface(String dbName, String path, long cacheSize)
    {
        sophiaEnvPtr = sp_env();
        sp_setstring(sophiaEnvPtr, "sophia.path", path, 0);
        sp_setstring(sophiaEnvPtr, "db", dbName, 0);
        sp_setint(sophiaEnvPtr, "db."+ dbName+".compaction.cache", cacheSize);

        sp_open(sophiaEnvPtr);
    }

    //SP_API void *sp_env(void);
    private native long sp_env();

    //SP_API void  *sp_document(void*);
    private native long sp_document(long ptr);

    //SP_API int   sp_setstring(void*, const char*, const void*, int);
    private native int sp_setstring(long ptr, String key, String value, int size);

    //SP_API int   sp_setint(void*, const char*, int64_t);
    private native int sp_setint(long ptr, String path, long v);

    //SP_API void  *sp_getobject(void*, const char*);
    private native long sp_getobject(long ptr, String key);

    //SP_API void    *sp_getstring(void*, const char*, int*);
    private native long sp_getstring(long ptr, String path, int add_size);

    //SP_API int64_t  sp_getint(void*, const char*);
    private native long sp_getint(long ptr, String path);

    //SP_API int      sp_open(void*);
    private native int sp_open(long ptr);

    //SP_API int      sp_destroy(void*);
    private native int sp_destroy(long ptr);

    //SP_API int      sp_set(void*, void*);
    private native int sp_set(long ptr, long ptr_arg);

    //SP_API int      sp_upsert(void*, void*);
    private native int sp_upsert(long ptr, long ptr_arg);

    //SP_API int      sp_delete(void*, void*);
    private native int sp_delete(long ptr, long ptr_arg);

    //SP_API void    *sp_get(void*, void*);
    private native long sp_get(long ptr, long ptr_arg);

    //SP_API void    *sp_cursor(void*);
    private native long sp_cursor(long ptr);

    //SP_API void    *sp_begin(void*);
    private native long sp_begin(long ptr);

    //SP_API int      sp_commit(void*);
    private native int sp_commit(long ptr);
}