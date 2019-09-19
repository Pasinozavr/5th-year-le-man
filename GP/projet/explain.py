from redis import Redis

redis = Redis(host='flask-redis', port=6379)

def go():
    return 'blabla This Compose/Flask demo has been viewed %s time(s).' % redis.get('hits')
