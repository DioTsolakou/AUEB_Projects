from django.db import connections

#username, password = get_input_from_client()

with connections['GDPR'].cursor() as cursor:
    cursor.execute("call login(%s', %s')", (username, password, ));
    result = cursor.fetchone()
    print(result)


