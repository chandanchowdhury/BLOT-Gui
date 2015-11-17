EXODUS_DIR=/home/c/chandanchowdhury/SEACAS/cmds

EXODUS_FILE=c_final

CMD_FILE=blot_cmd.txt
# The file details will be included by the BlotState program.

# First rule is executed by default. So do 'make all' by default.
all: GENPS GENJPG

# Blot command
BLOT=blot

# Which device to send the Blot output to. Use CPS to generate PS file.
DEVICE=cps

# Generate the PS file.
GENPS: $(EXODUS_DIR)/$(EXODUS_FILE).e $(CMD_FILE)
	$(BLOT) --input $(CMD_FILE) $(EXODUS_DIR)/$(EXODUS_FILE).e $(DEVICE) --hardcopy $(EXODUS_FILE)


# Command to convert PS to JPG.
CONVERT=convert
# For some unknown reason Blot generated PS need to rotated.
CONVERT_FLAGS=-rotate 90

# Generate the JPG from the PS file using ImageMagic "convert" command.
GENJPG: $(EXODUS_FILE).ps
	convert $(CONVERT_FLAGS) $(EXODUS_FILE).ps $(EXODUS_FILE).jpg

# Clean up
clean:
	rm $(EXODUS_FILE).ps
	rm $(EXODUS_FILE).jpg