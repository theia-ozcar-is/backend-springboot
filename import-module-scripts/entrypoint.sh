#!/bin/bash

#Create .htpasswd file
if [ ! -f /var/www/.htpasswd ]; then touch /var/www/.htpasswd ; fi

#Create html directory
if [ ! -d /var/www/html ]; then mkdir /var/www/html ; fi

exec "$@"
